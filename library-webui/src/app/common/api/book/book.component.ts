import { Component, OnInit } from '@angular/core';
import { Page } from 'src/app/shared/Page';
import { BookService } from 'src/app/services/book.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  //ngx datatable parameters
  rows = [];
  cols = [];
  page = new Page();
  control = true;
  books = [];
  //search book form
  searchBookForm: FormGroup;
  
  constructor(private bookService : BookService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loadStaticPage();
  }
  loadStaticPage(){
    this.setPage({ offset: 0 });
    this.searchBookForm=  this.formBuilder.group({
      'name': [null, [Validators.minLength(3),Validators.required]]
    });

  }
  setPage(pageInfo) {
    this.page.page = pageInfo.offset;
    this.bookService.getAllPageable(this.page).subscribe(pagedData => {
      this.page.size = pagedData.size;
      this.page.page = pagedData.page;
      this.page.totalElements = pagedData.totalElements;
      this.rows = pagedData.content;
    });
  }

  searchBook(){
    if (!this.searchBookForm.valid) {
      return;
    }
    this.bookService.findAllByName(this.searchBookForm.value['name']).subscribe(res => {
      this.control = false;
      this.books = res;
    });
  }
  deleteBook(id){
    this.bookService.delete(id).subscribe(res =>{
      this.control = true;
      this.loadStaticPage();
    });

  }

  
  get sf() { return this.searchBookForm.controls; }
}
