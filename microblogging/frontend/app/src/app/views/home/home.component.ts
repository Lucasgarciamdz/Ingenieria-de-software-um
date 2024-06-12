import { Component, OnInit } from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {PublicationSummaryComponent} from "../../components/publication-summary/publication-summary.component";
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
  constructor() {
  }

  ngOnInit(): void {
  }

}
