import { Component, OnInit } from '@angular/core';
import { AuthorService } from 'src/app/services/author.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookService } from 'src/app/services/book.service';
import { Author } from '../Author';
import { BsModalRef } from 'ngx-bootstrap';
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

  showModal = true;

  //form parameters
  BookForm: FormGroup;
  modalRef: BsModalRef;

  constructor(private authorService: AuthorService,
              private bookService: BookService,
              private route: ActivatedRoute,
              private location: Location,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.loadAuthorDetail();
    });

    this.loadAuthorDetail();

    this.BookForm = this.formBuilder.group({
      'name': [null, [Validators.required]],
      'publisher': [null, [Validators.required]],
      'barcode': [null, [Validators.required]],
      'content': [null, [Validators.required]],
      'authorId': [this.id]
    });
    this.BookForm.value['authorId'] = this.id;
  }

  // loadAuthors(){
  //   this.authorService.getAll().subscribe(res => {
  //     this.authors = res;
  //   });
  // }

  loadAuthorDetail() {
    this.authorService.getById(this.id).subscribe(data => {
      this.author = data;
      this.books = data['books'];
    });
  }

  saveBook() {
    this.BookForm.value['authorId'] = this.id;
    if (!this.BookForm.valid) {
      return;
    }
    this.bookService.post(this.BookForm.value).subscribe(res => {
      console.log(res);
      this.loadAuthorDetail();
      this.BookForm.reset();
      this.showModal = false;

    });
  }

  get f() { return this.BookForm.controls; }

  deleteBook(id) {
    this.bookService.delete(id).subscribe(res => {
      if (res === true) {
        this.loadAuthorDetail();
      } else {
        this.loadAuthorDetail();

      }
    })
  }

  closeAndResetModal() {
    this.showModal = false;
  }

  backClicked() {
    this.location.back();
  }
  showbox() {
    this.showModal = true;
  }
}
