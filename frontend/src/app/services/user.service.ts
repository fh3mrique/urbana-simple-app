import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_CONFIG } from '../utils/requestConfig';
import { Observable } from 'rxjs';
import { IUser } from '../types/user/IUser';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private readonly _http: HttpClient) { }

  findAll(): Observable<IUser[]>{
    return this._http.get<IUser[]>(`${API_CONFIG.baseUrl}/api/users`)
  }

}
