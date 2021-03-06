/*  bootstrap.h
 *
 *
 *  Copyright (C) 2016 Toxic All Rights Reserved.
 *
 *  This file is part of Toxic.
 *
 *  Toxic is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Toxic is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Toxic.  If not, see <http://www.gnu.org/licenses/>.
 *
 */


#ifndef BOOTSTRAP_H
#define BOOTSTRAP_H
//#include "jni.h"
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <pthread.h>

//#include <tox/tox.h>

#include "misc_tools.h"
#include "nTox.h"




/* Manages connection to the Tox DHT network. */

/* Max size of an http response that we can store in Recv_Data */
#define MAX_RECV_CURL_DATA_SIZE 32767




void do_tox_connection(Tox *m);


//int load_DHT_nodeslist(void);
int load_DHT_nodeslist();


int Call_Json_From_Tox_Java(char *json);


#endif  /* BOOTSTRAP_H */
