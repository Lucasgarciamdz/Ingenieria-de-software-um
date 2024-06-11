import { Component, OnInit } from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {PublicationSummaryComponent} from "../../components/publication-summary/publication-summary.component";
import { PublicationsService } from "../../services/publications.service";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NavBarComponent,
    PublicationSummaryComponent,
    NgForOf,
    FormsModule,
    NgIf
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  publications: any[] = [];
  editorId: number | null = parseInt(localStorage.getItem('editorId') || '0', 10);
  searchText: string = '';
  showClearFilterButton: boolean = false;

  constructor(private publicationsService: PublicationsService) { }

  ngOnInit(): void {
    this.getTopFivePublications();
  }

  getTopFivePublications() {
    this.publicationsService.getAll().subscribe(
      (response: any) => {
        this.publications = response.slice(0, 5);
      },
      (error: any) => {
        console.error('Error al obtener las publicaciones', error);
      }
    );
  }

  filterPublications() {
    if (!this.searchText.trim()) {
      this.getTopFivePublications();
      this.showClearFilterButton = false;
    } else {
      const searchTextLower = this.searchText.toLowerCase();
      this.publicationsService.getAll().subscribe(
        (response: any) => {
          this.publications = response.filter((publication: any) =>
            publication.title.toLowerCase().includes(searchTextLower) ||
            publication.description.toLowerCase().includes(searchTextLower)
          );
          this.showClearFilterButton = true;
        },
        (error: any) => {
          console.error('Error al obtener las publicaciones', error);
        }
      );
    }
  }

  clearFilter() {
    this.getTopFivePublications();
    this.searchText = '';
    this.showClearFilterButton = false;
  }
}
