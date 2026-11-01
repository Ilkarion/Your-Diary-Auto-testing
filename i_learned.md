## Intro Page — Test Cases

### TC-INTRO-02: Change application language

- When using `WebElement` variables too often, my test sometimes showed an error: **"Stale Element Reference"**.

**Why?**

Every DOM element has its own internal ID. When the page is re-rendered, elements can be created again with new internal IDs. The old reference points to an element that no longer exists.

**Solution:**

For each operation, we find the element again. This makes sure that we always use a fresh element instead of an old reference.


## SignPageTest

### How `driver.get()` works

- If the browser window is **already open** (for example, after `@BeforeMethod` in `BaseTest.java`), and you call `driver.get()` a second time with a different URL **within the same test**, it will **not** open a second window.

Instead, it reuses the existing active browser window and simply overwrites the current URL, redirecting the same page to the new address.


### TC-SIGN-02: invalid_email_format_validation
- Standard browser validation popups(like 'invalid email format) are 
not part of the standard DOM tree, and their text messages change automatically based on the 
browser's language locale. 
Also, we cant find them by.xpath()

**Solution:**

- Instead of searching for invisible validation tooltip elements or checking translated text, we use Selenium's JavascriptExecutor to call the native HTML5 method checkValidity() directly on the input element:




