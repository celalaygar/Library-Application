import { Component, OnInit } from '@angular/core';
import { AuthorService } from 'src/app/services/author.service';
import { ActivatedRoute } from '@angular/router';
import {Location} from '@angular/common';
@Component({
  selector: 'app-author-detail',
  templateUrl: './author-detail.component.html',
  styleUrls: ['./author-detail.component.css']
})
export class AuthorDetailComponent implements OnInit {
  author;
  books = [];
  id: number;
  constructor(private authorService: AuthorService,
              private route: ActivatedRoute,
              private _location: Location) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.loadAuthorDetail();
      console.log('sending id : ' + this.id);
    });

  }

  loadAuthorDetail(){
    this.authorService.getById(this.id).subscribe(data => {
      this.author = data;
      this.books = data['books'];
      console.log(...this.books);
    });
  }
  backClicked() {
    this._location.back();
  }
}
