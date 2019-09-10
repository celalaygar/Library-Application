import { Injectable } from '@angular/core';
import { ApiService } from './general/api.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  STUDENT_PATH = '/student';
  STUDENT_PATH_PAGE = '/pagination';
  GET_BOOK_PATH = '/get-book';
  LEAVE_BOOK_PATH = '/leave-book';
  CITIES_PATH = '/cities';
  FIND_BY_TC_NO = '/find';
  constructor(private apiService: ApiService,
              private http: HttpClient) { }

  getAllPageable(page){
    return this.apiService.getAllPageable(this.STUDENT_PATH + this.STUDENT_PATH_PAGE, page ).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  getById(id: number): Observable<any> {
    return this.apiService.getById(this.STUDENT_PATH + '/' + id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  findAllByName(name: string): Observable<any> {
    return this.apiService.findAllByName(this.STUDENT_PATH + this.FIND_BY_TC_NO + '/' + name).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  post(params): Observable<any> {
    return this.apiService.post(this.STUDENT_PATH, params).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  put(id: number, book): Observable<any> {
    return this.apiService.put(this.STUDENT_PATH + '/' + id, book).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  getBook(params): Observable<any> {
    return this.apiService.patch(this.STUDENT_PATH + this.GET_BOOK_PATH, params).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  leaveBook(params): Observable<any> {
    return this.apiService.patch(this.STUDENT_PATH + this.LEAVE_BOOK_PATH, params).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  delete(id): Observable<any> {
    return this.apiService.delete(this.STUDENT_PATH + '/' + id).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  getAllCities(): Observable<any> {
    return this.apiService.getAll(this.STUDENT_PATH + this.CITIES_PATH).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
}
