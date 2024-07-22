export interface IBoardingPasses {
    id: number;
    number: number;
    status: boolean;
    typeBoardingPass: TypeBoardingPass;
}

export enum TypeBoardingPass {
    ESTUDANTE = 'ESTUDANTE',
    COMUM = 'COMUM',
    TRABALHADOR = 'TRABALHADOR'
}

/*   export  TypeBoardingPass :string = 'ESTUDANTE' | 'COMUM' | 'TRABALHADOR'; */