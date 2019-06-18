import { Injectable } from '@angular/core';
import { ApiService } from './general/api.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/internal/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  AUTHOR_PATH = '/author';
  AUTHOR_PATH_PAGE = '/pagination';

  constructor(private apiService: ApiService, private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.apiService.getAll(this.AUTHOR_PATH).pipe(map(
      res => {
        if (res) {
          return res;
        } else {
          return {};
        }
      }
    ));
  }
  getAllPageable(page){
    return this.apiService.getAllPageable(this.AUTHOR_PATH + this.AUTHOR_PATH_PAGE, page ).pipe(map(
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
    return this.apiService.getById(this.AUTHOR_PATH + '/' + id).pipe(map(
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
