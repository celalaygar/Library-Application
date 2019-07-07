import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Page } from 'src/app/shared/Page';
import { BookService } from 'src/app/services/book.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-get-book',
  templateUrl: './get-book.component.html',
  styleUrls: ['./get-book.component.css']
})
export class GetBookComponent implements OnInit {
  message: string | undefined;
  id: number;
  //ngx datatable parameters
  rows = [];
  cols = [];
  page = new Page();
  control = true;
  books = [];
  bookId: any ;
  //search book form
  searchBookForm: FormGroup;
  constructor(private route: ActivatedRoute,
              private location: Location,
              private bookService: BookService,
              private studentService: StudentService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.loadStaticPage();
  }

  loadStaticPage() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this.setPage({ offset: 0 });
    this.searchBookForm = this.formBuilder.group({
      'name': [null, [Validators.minLength(3), Validators.required]]
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
  searchBook() {
    if (!this.searchBookForm.valid) {
      return;
    }
    this.bookService.findAllByName(this.searchBookForm.value['name']).subscribe(
      res => {
        this.control = false;
        this.books = res;
        this.message = ' Kayıtlar bulunmuştur. ';
      },
      error => {
        this.message = ' Hay Aksi <strong>' + this.searchBookForm.value['name'] + '</strong> bu isimde bir kitap kaydı bulunamamıştır. ';
      });
  }
  getBook(id,bookId){
    this.bookService.getById(bookId).subscribe( res =>{
        if(res['student'] == null){
          this.getbookForPatching(id,bookId);
        } else {
          this.message = ' Kitabın kayıtlı bir sahibi vardır. ';
          console.log(this.message);
        }
      });
  }
  getbookForPatching(id,bookId){
    this.studentService.getBookForpatch(id, { "bookId": bookId} ).subscribe(
      res => {
        this.loadStaticPage();
        this.message = ' Kayıt işlemi başarılıdır. ';
        console.log(this.message);
      }
      , error => {
        this.message = ' Kayıt işlemi başarısız. : ' + error;
        console.log(this.message);
      }
    );
  }
  get sf() { return this.searchBookForm.controls; }
  backClicked() {
    this.location.back();
  }
}
