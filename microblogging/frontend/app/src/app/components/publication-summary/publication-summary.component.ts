import { Component, Input } from '@angular/core';
import { NgIf } from "@angular/common";

@Component({
  selector: 'app-publication-summary',
  standalone: true,
  templateUrl: './publication-summary.component.html',
  imports: [
    NgIf
  ],
  styleUrls: ['./publication-summary.component.css']
})
export class PublicationSummaryComponent {
  @Input() publicationUrl: string | undefined;
  @Input() publicationTitle: string | undefined;
  @Input() publicationCaption: string | undefined;
  @Input() publicationAuthor: boolean | undefined = true;
}
