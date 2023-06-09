/*
 *
 * Copyright (C) 2023, TopicQuests Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.topicquests.vcore.api;

/**
 * Here for writing sql queries to be used in database handlers
 * @author jackpark
 */
public interface IQueries {

    public static final String INSERT_ITEM =
            "INSERT INTO public.item (todo, done) VALUES ($1, $2)";

    public static final String LIST_ITEMS =
            "SELECT * FROM public.item";

    public static final String UPDATE_ITEM =
            "UPDATE public.item SET done=$1 WHERE id=$2";
    public static final String REMOVE_ITEM =
            "DELETE FROM public.item WHERE ID=$1";
}
