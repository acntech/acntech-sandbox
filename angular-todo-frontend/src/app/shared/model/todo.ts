export class Todo {

  constructor (

    public description: string,
    public owner?: string,
    public deadline?: string,
    public done?: boolean,
    public deleted?: boolean,
    public _links?: Array<MapConstructor>,

  ) {  }
}
