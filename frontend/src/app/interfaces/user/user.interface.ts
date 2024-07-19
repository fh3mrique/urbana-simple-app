import { IBoardingPasses } from "./boarding-pass.interface";

export interface IUser  {
    id: number;
    name: string;
    email: string;
    password: string
    boardingPasses: IBoardingPasses[];
}

