## Pages
- Intro Page
- Sign Page
- Create Diary Page through UI Sign Page
- Edit Diary Page through API Sign 

### Intro Page — Test Cases

**TC-INTRO-01: Change application theme**
- Check if theme is dark by default.
- Switch theme from Light to Dark.
- Switch theme from Dark to Light.
- Verify that the selected theme is applied correctly.

**TC-INTRO-02: Change application language**
- Switch language from Russian to English.
- Switch language from English to Russian.
- Verify that the selected language is applied correctly.

**TC-INTRO-04: Redirect unauthenticated user to Sign Page**
- Click the "Get Started" button.
- Verify that an unauthenticated user is redirected to the Sign Page through 'checkAuthUser' page.

### Sign Page — Test Cases

**TC-SIGN-01: Verify Sign Page UI elements**
- Check if the "Welcome Back" header is displayed.
- Check if the Email and Password input fields are displayed.
- Check if the "Launch Into Space" button is displayed.
- Check if the "Need an account? Sign up" link is displayed.

**TC-SIGN-02: Empty fields validation**
- Leave Email and Password fields empty.
- Click the "Launch Into Space" button.
- Verify that the form is not submitted and validation errors are triggered.

**TC-SIGN-03: Invalid email format validation**
- Enter an invalid email format.
- Enter a valid password.
- Click the "Launch Into Space" button.
- Verify that an appropriate email error message appears.

**TC-SIGN-03: Invalid password validation**
- Enter valid Email format.
- Enter invalid password format.
- Click the "Launch Into Space" button.
- Verify that an appropriate email error message appears.

**TC-SIGN-04: Navigate to Sign up page**
- Click the "Sign up" link at the bottom of the form.
- Verify that the user is redirected to the Sign-Up page.

### Create Diary Page through UI Sign Page

**TC-CREATE-01: Sign in through UI Sign Page**
- Sign in with a valid email and password.
- Verify correct redirection through the `/checkAuthUser` page to the `/user` page.

**TC-CREATE-02: Navigate to the "New Entry" page**
- Verify that the "New Entry" clickable option is displayed.
- Click on the "New Entry" option.

**TC-CREATE-03: Verify redirection and UI elements**
- Verify the correct URL.
- Check all important UI elements on the page.

**TC-CREATE-04: Create a new entry with valid data**
- Enter valid data into the `Title` field.
- Verify that today's date is set by default.
- Choose at least 1 feeling.
- Enter a minimum of 10 characters in the main text area.
- Add 1 tag (`test`).
- Add 1 color tag (`color_test`).
- Click the "Save" button.
- Verify the success message: `New record created`.
- Verify that all fields are cleared after creation, except for the `date`.
- Verify that the user remains on the same page after creating a new entry.

**TC-CREATE-05: Check quick-select suggestions**
- Create and save record with tags and color-tags
- Try to create new entry and check if old tag and color-tag available from quick-select suggestions list.


