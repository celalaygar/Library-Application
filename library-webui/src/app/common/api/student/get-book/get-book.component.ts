import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Page } from 'src/app/shared/Page';
import { BookService } from 'src/app/services/book.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { StudentService } from 'src/app/services/student.service';
import { Student } from '../Student';
import { AlertifyService } from 'src/app/services/alertify.service';

@Component({
  selector: 'app-get-book',
  templateUrl: './get-book.component.html',
  styleUrls: ['./get-book.component.css']
})
export class GetBookComponent implements OnInit {
  message: string | undefined;
  id: number;
  student = new  Student();

  //ngx datatable parameters
  rows = [];
  cols = [];
  page = new Page();
  control = true;
  books = [];
  bookId: any;

  //search book form
  searchBookForm: FormGroup;
  getbookForm: FormGroup;
  constructor(private route: ActivatedRoute,
              private location: Location,
              private alert: AlertifyService,
              private bookService: BookService,
              private studentService: StudentService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.control = true;
    this.loadStaticPage();
  }

  loadStaticPage() {
    //Select id
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this.studentService.getById(this.id).subscribe(
      res => {
      this.student = res;
      },
      error => {
        this.message = 'Bu id : ' + this.id + ' bir kullanıcı bulunamadı.';
    });

    //load getbook form
    this.getbookForm = this.formBuilder.group({
      'bookId': [null, [Validators.required]],
      'studentId': [this.id, [Validators.required]]
    });

    this.setPage({ offset: 0 });
    this.searchBookForm = this.formBuilder.group({
      'name': [null, [Validators.minLength(3), Validators.required]]
    });
  }

  searchBook() {
    if (!this.searchBookForm.valid) {
      return;
    }
    console.log(this.searchBookForm.value['name']);
    this.bookService.findAllByName(this.searchBookForm.value['name']).subscribe(
      res => {
        this.control = false;
        this.books = res;
        this.message = ' Kayıtlar bulunmuştur. ';
        this.alert.success(' Kayıtlar bulunmuştur. ');
      },
      error => {
        this.control = true;
        this.loadStaticPage();
        this.alert.error(' Hay Aksi bu isimde bir kitap kaydı bulunamamıştır. ');
        this.message = ' Hay Aksi bu isimde bir kitap kaydı bulunamamıştır. ';
      });
  }

  getBook(bookId) {
    this.message = null;
    this.getbookForm = this.formBuilder.group({
      'bookId': [bookId, [Validators.required]],
      'studentId': [this.id, [Validators.required]]
    });

    this.bookService.getById(bookId).subscribe(res => {
      if (res['student'] == null) {

        this.studentService.getBook(this.getbookForm.value).subscribe(
          res => {
            this.loadStaticPage();
            this.message = ' Kayıt işlemi başarılıdır. ';
            this.alert.success( ' Kayıt işlemi başarılıdır. ');
          }
          , error => {
            this.alert.error(' Kayıt işlemi başarısız. : ');
            this.message = ' Kayıt işlemi başarısız. : ';
          }
        );
      } else {
        this.alert.error( ' Kitabın kayıtlı bir sahibi vardır. ');
        this.message = ' Kitabın kayıtlı bir sahibi vardır. ';
      }
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
  get sf() { return this.searchBookForm.controls; }
  backClicked() {
    this.location.back();
  }
}
