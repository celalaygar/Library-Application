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
  BOOK_STATUS_PATH = '/statuses';
  constructor(private apiService: ApiService,
              private http: HttpClient) { }
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
