import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  currentUser = {};
  constructor() { }

  ngOnInit() { this.currentUser = JSON.parse(localStorage.getItem('currentUser')); }
}
