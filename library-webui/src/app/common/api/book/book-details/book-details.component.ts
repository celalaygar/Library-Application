import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  id: number;
  book: any;

  constructor(private route: ActivatedRoute,
              private location: Location,
              private bookService: BookService) { }

  ngOnInit() {
    this.loadBookDetails();
  }

  loadBookDetails(){
    this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this.bookService.getById(this.id).subscribe(res=>{
      this.book = res;
    });
  }
  backClicked() {
    this.location.back();
  }
}
