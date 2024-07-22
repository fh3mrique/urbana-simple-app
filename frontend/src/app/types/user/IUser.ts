import { IBoardingPasses } from "./boarding-pass.interface";

export interface IUser  {
    id?: any;
    name: string;
    email: string;
    password?: string
    boardingPasses?: IBoardingPasses[];
}