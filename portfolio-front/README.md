# PortfolioFront

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 21.2.27.

## Local backend and database

The backend requires Java 25. From `portfolio-back`, start it with:

```bash
./mvnw spring-boot:run
```

By default, the backend listens on `http://localhost:8080` and uses an in-memory H2 database. The database is initialized on startup and its data is lost when the backend stops. The H2 console is at `http://localhost:8080/h2-console`, with JDBC URL `jdbc:h2:mem:portfoliodb`, username `sa`, and an empty password.

Use the default Spring profile for this local setup. An active `prod` profile, `SPRING_DATASOURCE_*` environment variables, or settings in `portfolio-back/.env.local` can override the local database configuration; remove or adjust those overrides for local development.

The Angular development server proxies `/api/**` to the backend at `http://localhost:8080`. Production continues to use its existing same-origin `/api` routing; the proxy only applies to the development server.

## Development server

To start a local development server, run:

```bash
npm start
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Vitest](https://vitest.dev/) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
