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

## Generate form

The focus of this project is to generate a form based of a simple `[:map]` schema. Have not tested nor attempted to implement more complex structures.   

Consider schema defined [here](src/form_gen/frontend/schema.cljs). Individual key schemas have schema properties specified. It is possible to configure form layout, label and whether the schema key should be part of the generated form. 

List of properties recognized by form-gen: such as :
Schema property | Description | Default behaviour
--|--|--
:ui/render | If this property value is set to `true` the schema key will be part of generated form. | If unspecified, key will not be part of the generated form.
:ui/input-type | Defined type of form input value. | Default value is `text` 
:ui/label | Specifies the label of form input. | If undefined key name will be capitalized and used as a label. Example `:first-name` -> "First Name"
:ui/col-span | Form is displayed in grid. This value defines number of cols the generated input will be spanning. | If undefined schema will span an entire row.
:ui/continue-row? | Every schema is placed at the start of a new row. If this value is set to `true` schema will try to fit itself to remaining columns of the row left unoccupied by previous schema. | If unspecified schema will be rendered on the start of a new row. 
