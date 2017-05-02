import {PipeTransform, Pipe} from "@angular/core";

@Pipe({
  name: 'search',
  pure: false
})
export class SearchPipe implements PipeTransform {

  transform(items: any[], field : string, value : string): any[] {
    if (!items) {
      return [];
    }

    if(!value) {
      return items;
    }

    return items.filter(item => item[field].indexOf(value) > -1);
  }
}
