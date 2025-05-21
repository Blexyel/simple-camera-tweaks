foreach ($dir in (Get-ChildItem ./versions -Directory)) {
    $version = $dir.Name
    Write-Host "Processing: $version"
    & gradle build "-Pmcversion=$version"
}

# build latest
& gradle build