package org.topicquests.vcore.api;

/**
 * Here for writing sql queries to be used in database handlers
 * @author jackpark
 * @license Apache 2
 */
public interface IQueries {

    public static final String INSERT_ITEM =
            "INSERT INTO public.item (text, etime) VALUES ($1. $2)";

    public static final String LIST_ITEMS =
            "SELECT * FROM public.item LIMIT $1 OFFSET $2";
}
