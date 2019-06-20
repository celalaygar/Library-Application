import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthorService } from 'src/app/services/author.service';
import { Page } from 'src/app/shared/Page';
import { ModalDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {
  authors = [];
  rows = [];
  cols = [];
  page = new Page();


  constructor(private authorService: AuthorService) { }

  ngOnInit() {
    this.setPage({ offset: 0 });
  }
  loadAuthors(){
    this.authorService.getAll().subscribe(res => {
      this.authors = res;
    });
  }



  setPage(pageInfo) {
    this.page.page = pageInfo.offset;
    this.authorService.getAllPageable(this.page).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.page = pagedData.page;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
    });
  }

}
