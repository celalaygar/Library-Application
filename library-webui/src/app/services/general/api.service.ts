import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/internal/operators';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) {
  }

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getAll(path: string): Observable<any> {
    return this.http.get<any>(environment.API_BASE_PATH + path).pipe(catchError(this.formatError));
  }

  getAllPageable(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.get<any>(environment.API_BASE_PATH + path, {params}).pipe(catchError(this.formatError));
  }

  getById(path: string): Observable<any> {
    return this.http.get<any>(environment.API_BASE_PATH + path).pipe(catchError(this.formatError));
  }
  getByName(path: string): Observable<any> {
    return this.http.get<any>(environment.API_BASE_PATH + path).pipe(catchError(this.formatError));
  }
  findAllByName(path: string): Observable<any> {
    return this.http.get<any>(environment.API_BASE_PATH + path).pipe(catchError(this.formatError));
  }
  post(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.post(environment.API_BASE_PATH + path, params).pipe(catchError(this.formatError));
  }

  put(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.put(environment.API_BASE_PATH + path, JSON.stringify(params), this.httpOptions).pipe(catchError(this.formatError));
  }
  patch(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.patch(environment.API_BASE_PATH + path, params).pipe(catchError(this.formatError));
  }

  delete(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.http.delete(environment.API_BASE_PATH + path).pipe(catchError(this.formatError));
  }

  private formatError(error: any) {
    return Observable.call(environment.API_BASE_PATH + error.error);
  }
}
