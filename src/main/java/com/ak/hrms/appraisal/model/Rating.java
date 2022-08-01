package com.ak.hrms.appraisal.model;

public final class Rating {

	
	public static Integer get(int i) {
		 if (i >= RatingCache.lowRating && i <= RatingCache.highRating)
	            return RatingCache.ratingCache[i-1];
		return Integer.valueOf(i);
	}
	
	
	private static class RatingCache {
        static final int lowRating = 1;
        static final int highRating = 7;
        static final Integer ratingCache[];

        static {

        	ratingCache = new Integer[(highRating - lowRating) + 1];
            int j = lowRating;
            for(int k = 0; k < ratingCache.length; k++)
            	ratingCache[k] = Integer.valueOf(j++);
        }

        private RatingCache() {}
    }

	
}
