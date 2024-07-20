import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { IUser } from "../interfaces/user/user.interface";
import { TypeBoardingPass } from "../interfaces/user/boarding-pass.interface";
import { UserListResponse } from "../types/users-list-response";
import { HttpClient } from "@angular/common/http";


@Injectable({
    providedIn: 'root'
})
export class UserService {

    /* private readonly usersList: UserListResponse = [
        {
            "id": 1,
            "name": "John Doe",
            "email": "john.doe@example.com",
            password: 'passsower#3dwe',
            "boardingPasses": [
                {
                    "id": 1,
                    "number": 1001,
                    "status": true,
                    "typeBoardingPass": TypeBoardingPass.COMUM
                },
                {
                    "id": 2,
                    "number": 1002,
                    "status": false,
                    "typeBoardingPass": TypeBoardingPass.TRABALHADOR
                }
            ]
        },
        {
            "id": 2,
            "name": "Jane Smith",
            "email": "jane.smith@example.com",
            password: 'passsower#3dwe',
            "boardingPasses": [
                {
                    "id": 3,
                    "number": 1003,
                    "status": true,
                    "typeBoardingPass": TypeBoardingPass.ESTUDANTE
                }
            ]
        }
    ];

    getUsers(): Observable<UserListResponse>{
        return new Observable((observer) =>{
            setTimeout(()=>{
                observer.next(this.usersList);
                observer.complete()
            }, 3000)
        })
    } */

    private readonly apiUrl = 'http://localhost:8080/api/users';

    constructor(private http: HttpClient) { }

    getUsers(): Observable<UserListResponse> {
        return this.http.get<UserListResponse>(this.apiUrl);
    }

    deleteBoardingPass(userId: number, boardingPassId: number): Observable<void> {
        const url = `${this.apiUrl}/${userId}/boarding-passes/${boardingPassId}`;
        return this.http.delete<void>(url);
    }

    activateBoardingPass(userId: number, boardingPassId: number): Observable<void> {
        return this.http.put<void>(`${this.apiUrl}/${userId}/${boardingPassId}/activate`, {});
    }

    deactivateBoardingPass(userId: number, boardingPassId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiUrl}/${userId}/${boardingPassId}/deactivate`, {});
    }
} 