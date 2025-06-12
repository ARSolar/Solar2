#!/usr/bin/env sh
# Simplified Gradle Wrapper
DIR="$(cd "$(dirname "$0")" && pwd)"
exec gradle "${@}" --no-daemon
