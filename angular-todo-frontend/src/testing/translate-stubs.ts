import { Injectable } from "@angular/core";

@Injectable()
export class TranslateServiceStub {

  constructor() { }

  use():void {}

  instant(key: string):string {
    return key + '-translated';
  }
}
