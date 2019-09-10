import { Component, OnInit } from '@angular/core';
import { AuthorService } from 'src/app/services/author.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookService } from 'src/app/services/book.service';
import { Author } from '../Author';
import { BsModalRef } from 'ngx-bootstrap';
import { AlertifyService } from 'src/app/services/alertify.service';
@Component({
  selector: 'app-author-detail',
  templateUrl: './author-detail.component.html',
  styleUrls: ['./author-detail.component.css']
})
export class AuthorDetailComponent implements OnInit {
  //Author detail parameters
  author = new Author();
  books = [];
  id: number;
  authors = [];
  bookStatuses: Array<any> = [];
  //form parameters about book
  BookForm: FormGroup;
  modalRef: BsModalRef;
  updated = true;
  //form parameters about Author
  AuthorUpdateForm: FormGroup;
  showModal = true;

  constructor(private authorService: AuthorService,
              private bookService: BookService,
              private alert: AlertifyService,
              private route: ActivatedRoute,
              private location: Location,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.staticLoadPage();
    this.LoadAuthorUpdateForm(this.author);
  }
  staticLoadPage(){
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });

    this.BookForm = this.formBuilder.group({
      'name': [null, [Validators.required]],
      'publisher': [null, [Validators.required]],
      'barcode': [null, [Validators.required]],
      'content': [null, [Validators.required]],
      'bookStatus': [null, [Validators.required]],
      'authorId': [this.id]
    });
    this.BookForm.value['authorId'] = this.id;
    this.loadAuthorDetail();
    this.getAllBookStatus();
  }

  loadAuthorDetail() {
    this.authorService.getById(this.id).subscribe(response => {
      this.author = response;
      this.books = response['books'];
    });
  }
  LoadAuthorUpdateForm(res){
    this.updated=true;
    this.AuthorUpdateForm = this.formBuilder.group({
      'name': [res['name'], [Validators.required]],
      'surname': [res['surname'], [Validators.required]],
      'email': [res['email'], [Validators.required]],
      'phone': [res['phone'], [Validators.required]],
      'about': [res['about']]
    });
  }
  updateAuthor(){
    console.log('loading')
    if (!this.AuthorUpdateForm.valid) {
      return;
    }
    this.authorService.put(this.id,this.AuthorUpdateForm.value).subscribe(
      res => {
        this.staticLoadPage();
        this.LoadAuthorUpdateForm(res);
        if(res['id']==this.id){
          this.updated = false;
          this.alert.success('Yazar kayıtları güncellenmiştir.');
        }
      },
      error=>{
        this.alert.success('Yazar güncelleme işlemi başarısız. <br/>Daha sonra tekrar deneyiniz..');
      }
    );
  }
  saveBook() {
    this.BookForm.value['authorId'] = this.id;
    if (!this.BookForm.valid) {
      return;
    }
    this.bookService.post(this.BookForm.value).subscribe(
      res => {
        this.loadAuthorDetail();
        if(res['id'] > 0 ){
          this.showModal = false;
          this.alert.success('Kayıt işlemi başarılı.');
        }
        this.BookForm.reset();
      },
      error=>{
        this.staticLoadPage();
        this.LoadAuthorUpdateForm(this.author);
        this.alert.error('Kayıt işlemi başarısız.');
      }
    );
  }

  deleteBook(id) {
    this.bookService.delete(id).subscribe(
      res => {
        if (res === true) {
          this.loadAuthorDetail();
          this.alert.success('Silme işlemi başarılı.');
        } else {
          this.loadAuthorDetail();
          this.alert.error('Silme işlemi başarısız. <br/> Daha sonra tekrar deneyiniz.');
        }
      },
      error=>{
        this.alert.error('Silme işlemi başarısız. <br/> Daha sonra tekrar deneyiniz.');
      }
    );
  }
  getAllBookStatus(){
    this.bookService.getAllBookStatus().subscribe( res => {
      this.bookStatuses = res;
    });
  }
  backClicked() {
    this.location.back();
  }
  LoadInsertBookForm() {
    this.showModal = true;
  }
  get f1() { return this.BookForm.controls; }
  get f2() { return this.AuthorUpdateForm.controls; }
}
