import { FrontendAngular2Page } from './app.po';

describe('frontend-angular2 App', () => {
  let page: FrontendAngular2Page;

  beforeEach(() => {
    page = new FrontendAngular2Page();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
