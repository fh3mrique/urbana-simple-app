import { Injectable } from "@angular/core";
import { Observable } from "rxjs";


@Injectable({
    providedIn: 'root'
})
export class boardingPassService {
    private readonly boardingPassList: any = [
        {
            "id": 1,
            "number": 1001,
            "status": true,
            "typeBoardingPass": "COMUM"
        },
        {
            "id": 2,
            "number": 1002,
            "status": false,
            "typeBoardingPass": "ESTUDANTE"
        },
        {
            "id": 3,
            "number": 1003,
            "status": true,
            "typeBoardingPass": "TRABALHADOR"
        }
    ];

    getBoardingPasses(): Observable<any> {
        return new Observable((observer) => {
            setTimeout(() => {
                observer.next(this.boardingPassList);
                observer.complete();
            }, 3000);
        });
    }
}