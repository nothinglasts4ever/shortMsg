import { MSGMAILBOXPage } from './app.po';

describe('msg-mailbox App', () => {
  let page: MSGMAILBOXPage;

  beforeEach(() => {
    page = new MSGMAILBOXPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
