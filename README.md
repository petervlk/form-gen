# Form-Gen

Form-Gen is a personal pet project written in ClojureScript, aimed at experimenting with generating forms from [Malli](https://github.com/metosin/malli) schemas. It heavily relies on [Fork](https://github.com/luciodale/fork) in combination with [re-frame](https://github.com/day8/re-frame) to provide a seamless and interactive form generation experience. Forms are styled using [tailwindcss](https://github.com/tailwindlabs/tailwindcss). 

## Features

- **Form Generation**: Form-Gen uses [Malli](https://github.com/metosin/malli) schemas generate forms. Default form layout can be customized using specific schema properties.

- **Fork Integration**: By leveraging Fork, Form-Gen provides a rich set of UI components for creating interactive and responsive forms. These components can be easily customized and combined to create complex form layouts.

- **re-frame State Management**: The project is built on top of re-frame, which provides a reliable and efficient state management solution. It allows for easy handling of form data, validation, and form submission actions.

- **Validation**: Form-Gen performs validation on the form inputs based on the defined Malli schema. It ensures that the data entered by users adheres to the specified constraints, improving data integrity and accuracy.

- **Client-side Rendering**: The forms are rendered on the client-side, enabling fast and responsive user interactions. The dynamic nature of ClojureScript and re-frame ensures a smooth and efficient user experience without frequent page reloads.

## Installation

1. Clone the repository:

   ```shell
   git clone https://github.com/petervlk/form-gen.git
   ```

2. Navigate to the project directory:

   ```shell
   cd form-gen
   ```

3. Install the dependencies:

   ```shell
   npm install
   ```

4. Build css:

   ```shell
   npx tailwindcss -c tailwind.config.js -i ./resources-dev/global.css -o ./resources/public/assets/css/output.css
   ```

## Running the app

1. Start the nREPL server:

   ```shell
   clojure -M:repl
   ```

2. Connect to the REPL:
    - Emacs/Cider users can simply run `M-x cider-connect-cljs RET`
    - other need to use their editor of choice to connect to clojure repl and from there execute `(user/cljs-repl)`.

3. Access the app in your web browser at `http://localhost:9090`.

