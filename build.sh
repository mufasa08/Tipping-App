#!/bin/bash

# Copyright 2020 Google LLC. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.


# Exit on error
set -e

# Install preview deps
"${ANDROID_HOME}"/tools/bin/sdkmanager --channel=3 \
  "tools" "platform-tools" "build-tools;31.0.0" "platforms;android-31"

# Build

echo "Building full project"
# For a merged commit, build all configurations.
./gradlew clean ktlint build
