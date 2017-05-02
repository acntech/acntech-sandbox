import { TodoApplicationFrontendAngularPage } from './app.po';

describe('todo-application-frontend-angular App', () => {
  let page: TodoApplicationFrontendAngularPage;

  beforeEach(() => {
    page = new TodoApplicationFrontendAngularPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
