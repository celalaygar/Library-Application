import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { BookService } from 'src/app/services/book.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { Book } from '../Book';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  id: number;
  book = new Book();
  showModal = true;
  bookStatuses = [];

  //book update form parameters
  BookForm: FormGroup;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private bookService: BookService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loadBookDetails();
    this.LoadBookUpdateForm(this.book);
    // this.showModal = true;
    // this.BookForm = this.formBuilder.group({
    //   'name':       [this.book.name, [Validators.required]],
    //   'publisher':  [this.book.publisher, [Validators.required]],
    //   'barcode':    [this.book.barcode, [Validators.required]],
    //   'content':    [this.book.content, [Validators.required]],
    //   'bookStatus': [this.book.bookStatus, [Validators.required]],
    //   'authorId':   [this.book.author.id]
    // });
  }

  loadBookDetails() {
    this.getAllBookStatus();
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this.bookService.getById(this.id).subscribe(res => {
      this.book = res;
    });
  }
  LoadBookUpdateForm(res) {
    this.showModal = true;
    this.BookForm = this.formBuilder.group({
      'name':       [res.name, [Validators.required]],
      'publisher':  [res.publisher, [Validators.required]],
      'barcode':    [res.barcode, [Validators.required]],
      'content':    [res.content, [Validators.required]],
      'bookStatus': [res.bookStatus, [Validators.required]],
      'authorId':   [res.author.id]
    });
  }


  updateBook() {
    if (!this.BookForm.valid) {
      return;
    }
    this.bookService.put(this.id, this.BookForm.value).subscribe(res=>{
      this.loadBookDetails();
      this.LoadBookUpdateForm(this.book);
      this.showModal = false;

    });
  }


  getAllBookStatus(){
    this.bookService.getAllBookStatus().subscribe( res => {
      this.bookStatuses = res;
    });
  }
  get f1() { return this.BookForm.controls; }
  backClicked() {
    this.location.back();
  }
}
