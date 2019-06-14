import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthorService } from 'src/app/services/author.service';
import { Page } from 'src/app/shared/Page';

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
    //this.loadAuthors();
    this.setPage({ offset: 0 });
  }
  loadAuthors(){
    this.authorService.getAll().subscribe(res => {
      this.authors = res;
      console.log(...this.authors);
    });
  }



  setPage(pageInfo) {
    console.log(pageInfo.offset)
    this.page.page = pageInfo.offset;
    this.authorService.getAllPageable(this.page).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.page = pagedData.page;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
    });
  }

}
