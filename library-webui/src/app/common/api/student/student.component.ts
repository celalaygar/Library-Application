import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from 'src/app/services/student.service';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  //student insert form parameters
  cities: Array<any> = [];
  StudentInsertForm: FormGroup;
  showModal = true;
  constructor(private route: ActivatedRoute,
              private studentService: StudentService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.LoadStudentInsertForm();
  }

  LoadStudentInsertForm(){
    this.showModal = true;
    this.StudentInsertForm = this.formBuilder.group({
      'fullname':     [null, [Validators.required]],
      'tcNo':         [null, [Validators.min(10000000000),Validators.max(100000000000),Validators.required]],
      'email':        [null, [Validators.required,Validators.email]],
      'phone':        [null, [Validators.required]],
      'address':      [null, [Validators.required]],
      'city':         [null, [Validators.required]],
      'university':   [null, [Validators.required]],
      'department':   [null, [Validators.required]],
    });
    this.getAllCities();
  }

  insertStudent(){
    if (!this.StudentInsertForm.valid) {
      return;
    }
    this.studentService.post(this.StudentInsertForm.value).subscribe(res=>{
      this.LoadStudentInsertForm();
      console.log("insert student");
      this.showModal = false;
    });
  }

  getAllCities(){
    this.studentService.getAllCities().subscribe(res => {
      this.cities = res;
    });
  }

  get sif() { return this.StudentInsertForm.controls; }
}
