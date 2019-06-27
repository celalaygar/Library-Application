import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './general/api.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  BOOK_PATH = '/book';
  BOOK_PATH_PAGE = '/pagination';
  BOOK_STATUS_PATH = '/statuses';
  FIND_BY_NAME = '/find';
  constructor(private apiService: ApiService,
              private http: HttpClient) { }

  getAllPageable(page){
    return this.apiService.getAllPageable(this.BOOK_PATH + this.BOOK_PATH_PAGE, page ).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }

  getAllBookStatus(): Observable<any> {
    return this.apiService.getAll(this.BOOK_PATH + this.BOOK_STATUS_PATH).pipe(map(
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
    return this.apiService.getById(this.BOOK_PATH + '/' + id).pipe(map(
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
    return this.apiService.findAllByName(this.BOOK_PATH + this.FIND_BY_NAME + '/' + name).pipe(map(
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
    return this.apiService.post(this.BOOK_PATH, params).pipe(map(
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
    return this.apiService.put(this.BOOK_PATH + '/' + id, book).pipe(map(
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
    return this.apiService.delete(this.BOOK_PATH + '/' + id).pipe(map(
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
