import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {
  id: number;

  //student update form parameters
  StudentUpdateForm: FormGroup;
  showModal = true;
  constructor(private route: ActivatedRoute,
              private location: Location,
              private bookService: BookService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.loadStudentDetails();
  }
  loadStudentDetails() {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      console.log(this.id);
    });
  }

  LoadStudentUpdateForm(res){
    console.log(res);
    this.showModal = true;
    this.StudentUpdateForm = this.formBuilder.group({
      'fullname':     [null, [Validators.required]],
      'tcNo':         [null, [Validators.min(10000000000),Validators.max(100000000000),Validators.required]],
      'email':        [null, [Validators.required,Validators.email]],
      'phone':        [null, [Validators.required]],
      'address':       [null, [Validators.required]],
      'university':   [null, [Validators.required]],
      'department':   [null, [Validators.required]],
    });
  }


  updateStudent(){
    if (!this.StudentUpdateForm.valid) {
      return;
    }
    this.showModal = false;
    console.log("updated student")
  }

  get suf() { return this.StudentUpdateForm.controls; }
  backClicked() {
    this.location.back();
  }
}
