import {Component, OnInit} from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {ActivatedRoute, Router} from "@angular/router";
import {PublicationsService} from "../../services/publications.service";
import {ImagesService} from "../../services/images.service";
import {FormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import { ChangeDetectorRef } from '@angular/core';


@Component({
  selector: 'app-publication',
  standalone: true,
  imports: [
    NavBarComponent,
    FormsModule,
    NgIf
  ],
  templateUrl: './publication.component.html',
  styleUrl: './publication.component.css'
})
export class PublicationComponent implements OnInit {
  publicationId: number = 0;
  imageId: number = 0;
  publication: any;
  imageDataUrl: string = '';
  editButton: boolean= false;
  isEditMode: boolean = false;
  newImage: string = '';
  fileLabel: string = 'Select file';

  constructor(
    private route: ActivatedRoute,
    private publicationsService: PublicationsService,
    private imagesService: ImagesService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.fileLabel = 'Select file';
    this.route.params.subscribe(params => {
      this.publicationId = params['id'];
      this.publicationsService.get(this.publicationId).subscribe(
        (response: any) => {
          this.publication = response;
          this.editButton = this.getEditorId() === this.publication.editor_id;
          this.imagesService.getImageByPublicationId(this.publicationId).subscribe(
            (imageResponse: any) => {
              this.imageId = imageResponse.image_id;
              this.imageDataUrl = 'data:image/jpeg;base64,' + imageResponse.image;
            },
            (imageError: any) => {
              console.error('Error al obtener la imagen de la publicación', imageError);
            }
          );
        },
        (error: any) => {
          console.error('Error al obtener la publicación', error);
        }
      );
    });
  }

  getEditorId(): number {
    return parseInt(localStorage.getItem('editorId') || '0', 10);
  }

  toggleEditMode(): void {
    this.isEditMode = true;
  }

  cancelEditMode(): void {
    this.isEditMode = false;
  }

  onFileChanged(event: any): void {
    const file: File = event.target.files[0];

    if (file.type === 'image/png') {
      const reader = new FileReader();

      reader.onloadend = () => {
        const imageDataUrl = reader.result as string;
        this.newImage = imageDataUrl.split(',')[1];
        console.log('Imagen cargada', this.newImage.length);
        this.fileLabel = 'File selected';
        this.cdr.detectChanges();
      };
      reader.readAsDataURL(file);
    }
  }


  cancelFileSelection(): void {
    this.newImage = '';
    this.fileLabel = 'Select file';
  }

  updatePublication(): void {
    const publicationTemplate = {
      editor_id: this.publication.editor_id,
      title: this.publication.title,
      description: this.publication.description,
      creation_date: this.publication.creation_date,
    };

    this.publicationsService.update(this.publicationId, publicationTemplate).subscribe(
      (response: any) => {
        console.log('Publicación actualizada con éxito', response);

        if (this.newImage) {
          const imageTemplate = {
            publication_id: this.publication.publication_id,
            image: this.newImage,
            description: "Nueva imagen",
          };
          if (this.imageId === undefined) {
            this.imagesService.create(imageTemplate).subscribe(
              (imageResponse: any) => {
                console.log('Imagen creada con éxito', imageResponse);
              },
              (imageError: any) => {
                console.error('Error al crear la imagen', imageError);
              }
            );
          }
          else {
            console.log('Actualizando imagen ESAAAAAAA');
            this.imagesService.update(this.imageId, imageTemplate).subscribe(
              (imageResponse: any) => {
                console.log('Imagen actualizada con éxito', imageResponse);
              },
              (imageError: any) => {
                console.error('Error al actualizar la imagen', imageError);
              }
            );
          }
        }
      },
      (error: any) => {
        console.error('Error al actualizar la publicación', error);
      }
    );
    this.router.navigate(['/home/']);

  }
}
