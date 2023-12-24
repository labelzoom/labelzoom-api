![LabelZoom Logo](LabelZoom_Logo_f_400px.png)

# labelzoom-api

[![Build Status](https://github.com/labelzoom/labelzoom-api/actions/workflows/gradle-build.yml/badge.svg?branch=main)](https://github.com/labelzoom/labelzoom-api/actions?query=branch%3Amain)
[![Release](https://img.shields.io/github/release/labelzoom/labelzoom-api.svg?style=flat-square)](https://github.com/labelzoom/labelzoom-api/releases)

The LabelZoom API is the foundation on which all of LabelZoom's products and services are built. The API serves to define the following:
- **Data model:** [/src/main/java/com/labelzoom/api/model](/src/main/java/com/labelzoom/api/model) - The data model of a LabelZoom label
- **I/O interfaces**
  - **Input**: [/src/main/java/com/labelzoom/api/input](/src/main/java/com/labelzoom/api/input) - The LabelReader interface, which can be implemented to convert other types of files into LabelZoom labels
  - **Output:** [/src/main/java/com/labelzoom/api/output](/src/main/java/com/labelzoom/api/output) - The LabelWriter interface, which can be implemented to convert LabelZoom labels into other types of files
- **Services:** [/src/main/java/com/labelzoom/api/services](/src/main/java/com/labelzoom/api/services) - Various interfaces that support the implementations of the I/O interfaces
- **Utilities**
  - [/src/main/java/com/labelzoom/api/diagnostics](/src/main/java/com/labelzoom/api/diagnostics) - Lightweight utilities to measure the performance of the implemented interfaces
  - [/src/main/java/com/labelzoom/api/util](/src/main/java/com/labelzoom/api/util) - Domain-centric utilities aligned to the business domain (e.g. 2D graphics, image manipulation, etc.)
