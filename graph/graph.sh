#!/bin/bash

echo "== Creation Courbes =="
echo ""
echo "Courbe : PerformanceK.png "
./script/performanceK.pg
echo "Courbe : Performance.png "
./script/performance.pg
echo "Courbe : PerformancePuissanceExacte.png "
./script/performancePuissanceExacte.pg
echo "Courbe : PerformanceEpsilon.png "
./script/performanceEpsilon.pg
echo "Courbe : NombreItEnFonctionDeK.png "
./script/NombreItEnFonctionDeK.pg
echo "Courbe : ValeursProches.png "
./script/ValeursProches.pg
echo "Courbe : ValeursEloignees.png "
./script/ValeursEloignees.pg

