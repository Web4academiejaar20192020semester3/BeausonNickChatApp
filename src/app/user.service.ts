import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from './user';
import { USERS } from './mock-users';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = 'http://localhost:8080/Controller';

  httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json'}
    )};

  constructor(private http: HttpClient) {} // instantie van httpClient

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl)  // User[]-> cast naar array van users
      .pipe(catchError(this.handleError<User[]>('getUsers', []))
      );
  }

  /** PUT: update the user on the server */
  updateUser(user: User): Observable<any> {
    console.log('updateUser called');
    return this.http.put(this.usersUrl, user, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateUser'))
    );
  }



  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      alert('error');
      console.error(error);
      console.log('error: ' + error);
      return of (result as T);
    };
  }

/*
  getUsers(): Observable<User[]> {
    return of(USERS);
  }

  constructor() {}
*/
 /* constructor(private http: HttpClient) {
  }

 // private userUrl = 'api/users';   //URL to web api*/

}
