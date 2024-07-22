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

  findById(id: number): Observable<IUser> {
    return this._http.get<IUser>(`${API_CONFIG.baseUrl}/api/users/${id}`);
  }

  findAll(): Observable<IUser[]> {
    return this._http.get<IUser[]>(`${API_CONFIG.baseUrl}/api/users`)
  }

  create(user: IUser): Observable<IUser[]> {
    return this._http.post<IUser[]>(`${API_CONFIG.baseUrl}/api/users`, user)
  }

  update(user: IUser): Observable<IUser> {
    return this._http.put<IUser>(`${API_CONFIG.baseUrl}/api/users/${user.id}`, user)
  }

  deleteUser(userId: number): Observable<void> {
    return this._http.delete<void>(`${API_CONFIG.baseUrl}/api/users/${userId}`);
  }

  deleteBoardingPass(userId: number, boardingPassId: number): Observable<void> {
    const url = `${API_CONFIG.baseUrl}/api/users/${userId}/boarding-passes/${boardingPassId}`;
    return this._http.delete<void>(url);
  }

  activateBoardingPass(userId: number, boardingPassId: number): Observable<void> {
    return this._http.put<void>(`${API_CONFIG.baseUrl}/api/users/${userId}/${boardingPassId}/activate`, {});
  }

  deactivateBoardingPass(userId: number, boardingPassId: number): Observable<void> {
    return this._http.delete<void>(`${API_CONFIG.baseUrl}/api/users/${userId}/${boardingPassId}/deactivate`, {});
  }

}
