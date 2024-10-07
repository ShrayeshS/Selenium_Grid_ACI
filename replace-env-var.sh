#!/bin/bash

set -e
HTML_FILE=/app/calculator/calculator/Views/Home/Index.cshtml

sed -i "s/{{ENV}}/${ENV}/g" $HTML_FILE