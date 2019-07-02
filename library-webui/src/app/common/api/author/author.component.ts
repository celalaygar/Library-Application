import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { AuthorService } from 'src/app/services/author.service';
import { Page } from 'src/app/shared/Page';
import { ModalDirective } from 'ngx-bootstrap';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

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
  control = true;
  controlAuthorForm = true;
  //form parameters
  AuthorForm: FormGroup;
  searchForm: FormGroup;
  constructor(private authorService: AuthorService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loadStaticPage();
  }

  loadStaticPage(){
    this.setPage({ offset: 0 });
    this.searchForm=  this.formBuilder.group({
      'name': [null, [Validators.minLength(3)]]
    });
    this.AuthorForm = this.formBuilder.group({
      'name': [null, [Validators.required]],
      'surname': [null, [Validators.required]],
      'about': [null, [Validators.required]],
      'email': [null, [Validators.email]],
      'phone': [null, [Validators.required]],
    });
  }

  loadAuthors(){
    this.authorService.getAll().subscribe(res => {
      this.authors = res;
    });
  }

  loadAuthorFormPanel(){
    this.controlAuthorForm=true;
    this.AuthorForm = this.formBuilder.group({
      'name': [null, [Validators.required]],
      'surname': [null, [Validators.required]],
      'about': [null, [Validators.required]],
      'email': [null, [Validators.email,Validators.required]],
      'phone': [null, [Validators.required]],
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
  saveAuthor(){
    if (!this.AuthorForm.valid) {
      return;
    }
    this.authorService.post(this.AuthorForm.value).subscribe(res => {
      console.log(res);
      this.AuthorForm.reset();
      this.setPage({ offset: 0 });
      this.controlAuthorForm = false;
    });
  }
  deleteAuthor(id){
    console.log(id);
    this.authorService.delete(id).subscribe(res=>{
      this.setPage({ offset: 0 });
      this.control = true;
      this.loadStaticPage();
    });
  }

  searchAuthor(){
    if (!this.searchForm.valid) {
      return;
    }
    this.authorService.findAllByName(this.searchForm.value['name']).subscribe(res => {
      this.authors = res;
      this.control=false;
    });
  }

  get sf() { return this.searchForm.controls; }
  get f() { return this.AuthorForm.controls; }
}
