# Spark jobs that makes insights about the countries

## $class_name$App
TODO: Describe the purpose of the job here

### Input parameters

**From CLI**

| Name                | Description                     | Mandatory | Default | Example                |
| ------------------- | ------------------------------- | --------- | ------- | ---------------------- |
| `--world-data-path` | Input world data path           | yes       |         | `/path/to/world/data/` |
| `--countries-path`  | Input countries data path       | yes       |         | `/path/to/countries/`  |
| `--source-format`   | Source format                   | no        | `csv`   | `delta`                |
| `--target-path`     | Sink location path              | no        |         | `/path/to/target`      |
| `--target-format`   | Sink format if location defined | no        | `delta` | `parquet`              |
| `--target-table`    | Sink table                      | no        |         | `db.table`             |

## Building and running unit tests

```bash
sbt clean compile test
```

## Releasing a new version

```bash
git tag v0.0.1
git push origin v0.0.1
```
