# Contributing Guide

## Branching Guidelines

New branches should be named with the following format:

```
{initials}_{relevant action or task}
```

For example:

```
hlm_action50
```

## Pull Requests

Follow the pull request template format found here [here](.github/PULL_REQUEST_TEMPLATE.md)

```
# List Any Relevant Actions or Tasks

## Actions

...

## Tasks

...

# Description of Changes

...

# Relevant Artifacts

...

# Other Comments

...

```

## Merging

Before a pull request can be merged into the main branch, the following requirements must be met:

1. The pull request must pass the validator workflow
2. The pull request must be approved by at least 2 other contributors

## Advice

To ensure the code is well formatted, and passes the validator, you can run the following command to format the code automatically:

```sh
mvn formatter:format
```

If you would like to run the validator locally to check if it passes, run:

```sh
mvn formatter:validate
```
