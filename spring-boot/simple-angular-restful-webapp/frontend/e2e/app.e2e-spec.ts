import { SimpleAngularRestfulWebappFrontendPage } from './app.po';

describe('simple-angular-restful-webapp-frontend App', () => {
  let page: SimpleAngularRestfulWebappFrontendPage;

  beforeEach(() => {
    page = new SimpleAngularRestfulWebappFrontendPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
