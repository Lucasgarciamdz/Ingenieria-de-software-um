import { Component, OnInit } from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {PublicationSummaryComponent} from "../../components/publication-summary/publication-summary.component";
import { PublicationsService } from "../../services/publications.service";
import {NgFor} from "@angular/common";

@Component({
  selector: 'app-my-publications',
  standalone: true,
  imports: [
    NavBarComponent,
    PublicationSummaryComponent,
    NgFor
  ],
  templateUrl: './my-publications.component.html',
  styleUrl: './my-publications.component.css'
})
export class MyPublicationsComponent implements OnInit {
  publications: any[] = [];
  editorId: number | null = parseInt(localStorage.getItem('editorId') || '');

  constructor(private publicationsService: PublicationsService) { }

  ngOnInit() {
    this.getPublicationsByEditor();
  }

  getPublicationsByEditor() {
    if (this.editorId) {
      console.log('editorId:', this.editorId);
      this.publicationsService.getPublicationsByEditor(this.editorId).subscribe(
        (response) => {
          this.publications = response;
          console.log('Publicaciones:', this.publications);
        },
        (error) => {
          console.error('Error obteniendo publicaciones:', error);
        }
      );
    } else {
      console.error('Error: editorId no es un número válido');
    }
  }
}
